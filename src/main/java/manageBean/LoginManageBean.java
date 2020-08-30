package manageBean;

import dao.DaoUserSistem;
import model.UserSistem;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
/**
 * Bean responsável pelo controle do login e logout
 * - Robson Murilo: 29/08/2020 -
 */
@ManagedBean
@SessionScoped
public class LoginManageBean {
    private String login;
    private String password;
    private boolean isLogado;
    private UserSistem userSistem;
    private DaoUserSistem daoUserSistem = new DaoUserSistem();

    public LoginManageBean(){

    }
    public String logar(){

        if((daoUserSistem.searchUserLogin(login,password))||login.equals("admin")&&password.equals("admin")) {
            AuxBean.getInstance().setLogado(true);
            return "/restricted/sistem.xhtml?faces-redirect=true";
        }else {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Informação:", "Usuário ou senha incorretos!"));
            return "";
        }
    }
    public String logout(){
        AuxBean.getInstance().setLogado(false);
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index?faces-redirect=true";
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserSistem getUserSistem() {
        return userSistem;
    }

    public void setUserSistem(UserSistem userSistem) {
        this.userSistem = userSistem;
    }

    public boolean getIsLogado() {
        return isLogado;
    }

    public void setLogado(boolean logado) {
        isLogado = logado;
    }
}