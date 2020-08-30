package dao;

import model.UserSistem;
import javax.persistence.Query;
import java.util.List;
/**
 * Classe responsavel pelas operações especificas referentes ao usuário do sistema.
 * - Robson Murilo: 29/08/2020 -
 */
public class DaoUserSistem extends DaoGerenic<UserSistem> {

    public void removeUserSistem(UserSistem pessoa) throws  Exception{
        getEntityManager().getTransaction().begin();
        String sqlDelFone = "delete from phoneuser where usersistem_id = "+pessoa.getId();
        getEntityManager().createNativeQuery(sqlDelFone).executeUpdate();
        getEntityManager().getTransaction().commit();
        super.deleteID(pessoa);
    }
    public boolean searchUserLogin(String login, String password){
        DaoGerenic<UserSistem> daoGerenic = new DaoGerenic<UserSistem>();
        List<UserSistem> list = daoGerenic.getEntityManager().createQuery("from UserSistem ").getResultList();

        for (UserSistem userSistem : list) {
            if (userSistem.getEmail().equals(login) && userSistem.getPasswordUser().equals(password)){
                return true;
            }
        }
        return false;
    }


    public List<UserSistem> searchName(String searchFild) {
        Query query = super.getEntityManager().createQuery("from UserSistem where name like '%"+searchFild+"%'");
        return query.getResultList();
    }
}
