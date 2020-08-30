package manageBean;
/**
 * Bean auxiliar para controle de login
 * - Robson Murilo: 29/08/2020 -
 */
public class AuxBean {
    private static AuxBean instance;
    private boolean logado;
    public static AuxBean getInstance(){
        if (instance == null){
            instance = new AuxBean();
        }
        return instance;
    }
    private AuxBean(){

    }

    public boolean isLogado() {
        return logado;
    }

    public void setLogado(boolean logado) {
        this.logado = logado;
    }
}
