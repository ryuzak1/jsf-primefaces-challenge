package manageBean;

import dao.DaoGerenic;
import dao.DaoPhone;
import model.PhoneUser;
import model.UserSistem;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
/**
 * Bean responsável pelo gerencialmento dos telefones de um usuário
 * - Robson Murilo: 29/08/2020 -
 */
@ManagedBean
@ViewScoped
public class PhoneManageBean {

    private UserSistem userSistem = new UserSistem();
    private DaoGerenic<UserSistem> daoGerenic = new DaoGerenic<>();
    private DaoPhone<PhoneUser> daoPhone = new DaoPhone<PhoneUser>();
    private PhoneUser phoneUser = new PhoneUser();


    @PostConstruct
    public void init(){
        String idUsuario = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idUser");
        userSistem = daoGerenic.searchGenericId(Long.parseLong(idUsuario),UserSistem.class);
    }
    public String salvarFone(){
        phoneUser.setUserSistem(userSistem);
        daoPhone.saveGeneric(phoneUser);
        phoneUser = new PhoneUser();
        userSistem = daoGerenic.searchGenericId(userSistem.getId(),UserSistem.class);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,"Informação:","Salvo com Sucesso!"));
        return "";
    }

    public String removePhone() throws Exception {
        daoPhone.deleteID(phoneUser);
        userSistem = daoGerenic.searchGenericId(userSistem.getId(),UserSistem.class);
        phoneUser = new PhoneUser();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,"Informação:","Telefone Removido!"));
        return "";
    }

    public UserSistem getUserSistem() {
        return userSistem;
    }

    public void setUserSistem(UserSistem userSistem) {
        this.userSistem = userSistem;
    }

    public DaoPhone<PhoneUser> getDaoPhone() {
        return daoPhone;
    }

    public void setDaoPhone(DaoPhone<PhoneUser> daoPhone) {
        this.daoPhone = daoPhone;
    }

    public PhoneUser getPhoneUser() {
        return phoneUser;
    }

    public void setPhoneUser(PhoneUser phoneUser) {
        this.phoneUser = phoneUser;
    }
}
