package manageBean;

/**
 * Bean responsável pelo CRUD dos usuários.
 * - Robson Murilo: 29/08/2020 -
 */

import com.google.gson.Gson;
import dao.DaoGerenic;
import dao.DaoUserSistem;
import model.UserSistem;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class UserSistemManageBean {
    private UserSistem userSistem = new UserSistem();
    private DaoGerenic<UserSistem> daoGerenic = new DaoGerenic<>();
    private List<UserSistem> userSistemList = new ArrayList<UserSistem>();
    private DaoUserSistem daoUsuario = new DaoUserSistem();
    private String searchfild;


    @PostConstruct
    public void init() {
        userSistemList = daoGerenic.listGeneric(UserSistem.class);
    }

    public UserSistem getUserSistem() {
        return userSistem;
    }

    public void setUserSistem(UserSistem userSistem) {
        this.userSistem = userSistem;
    }

    public String getSearchfild() {
        return searchfild;
    }

    public void setSearchfild(String searchfild) {
        this.searchfild = searchfild;
    }

    public String salvar() {
        daoGerenic.saveGeneric(userSistem);
        userSistemList.add(userSistem);
        userSistem = new UserSistem();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação:", "Salvo com Sucesso!"));
        return "";
    }

    public void search(){

        userSistemList = daoUsuario.searchName(searchfild);
    }

    public String newRegister() {
        userSistem = new UserSistem();
        return "";
    }

    public List<UserSistem> getUserSistemList() {

        return userSistemList;
    }

    public String deletar() {
        try {
            daoUsuario.removeUserSistem(userSistem);
            userSistemList.remove(userSistem);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Informação:", "Deletado com Sucesso!"));
            userSistem = new UserSistem();

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informação:", "Erro ao tentar deletar!"));
        }
        return "";
    }
    //Busca na API do viacep.
    public void searchZipCode(AjaxBehaviorEvent event){
        try {
            URL url = new URL("https://viacep.com.br/ws/"+ userSistem.getCep()+"/json/");
            URLConnection  connection = url.openConnection();
            InputStream is = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));

            String auxCep;
            StringBuilder jsonCep = new StringBuilder();
            while ((auxCep = br.readLine())!=null){
                jsonCep.append(auxCep);
            }

            UserSistem usuarioPessoaCep = new Gson().fromJson(jsonCep.toString(),UserSistem.class);
            System.out.println(usuarioPessoaCep);
            userSistem.setCep(usuarioPessoaCep.getCep());
            userSistem.setBairro(usuarioPessoaCep.getBairro());
            userSistem.setLocalidade(usuarioPessoaCep.getLocalidade());
            userSistem.setUf(usuarioPessoaCep.getUf());


        }catch (Exception e){
            e.printStackTrace();
        }

    }



}
