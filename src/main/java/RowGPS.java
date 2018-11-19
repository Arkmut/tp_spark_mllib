import java.io.Serializable;
import java.lang.reflect.Field;

public class RowGPS implements Serializable {

    private static final long serialVersionUID = 1L;
    String id;
    String nom_voie;
    String id_fantoir;
    int numero;
    String rep;
    int insee;
    int codepost;
    String alias;
    String nom_ld;
    String nom_afnor;
    String libelle_acheminement;
    double x;
    double y;
    double lng;
    double lat;
    String nom_commune;

    public static RowGPS fromCSVRow(String row,String delimiter){
        RowGPS r = new RowGPS();
        String[] splitted = row.split(delimiter);
        Field[] fields= RowGPS.class.getDeclaredFields();
        for(int i = 0;i<splitted.length;i++){
            try {
                fields[i].set(r,splitted[i]);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return r;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom_voie() {
        return nom_voie;
    }

    public void setNom_voie(String nom_voie) {
        this.nom_voie = nom_voie;
    }

    public String getId_fantoir() {
        return id_fantoir;
    }

    public void setId_fantoir(String id_fantoir) {
        this.id_fantoir = id_fantoir;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getRep() {
        return rep;
    }

    public void setRep(String rep) {
        this.rep = rep;
    }

    public int getInsee() {
        return insee;
    }

    public void setInsee(int insee) {
        this.insee = insee;
    }

    public int getCodepost() {
        return codepost;
    }

    public void setCodepost(int codepost) {
        this.codepost = codepost;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getNom_ld() {
        return nom_ld;
    }

    public void setNom_ld(String nom_ld) {
        this.nom_ld = nom_ld;
    }

    public String getNom_afnor() {
        return nom_afnor;
    }

    public void setNom_afnor(String nom_afnor) {
        this.nom_afnor = nom_afnor;
    }

    public String getLibelle_acheminement() {
        return libelle_acheminement;
    }

    public void setLibelle_acheminement(String libelle_acheminement) {
        this.libelle_acheminement = libelle_acheminement;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getNom_commune() {
        return nom_commune;
    }

    public void setNom_commune(String nom_commune) {
        this.nom_commune = nom_commune;
    }
}
