package modelo;

public class Alumno {

    private String numControl;

    private String nombre;

    private String primerAp;

    private  String segundoAp;

    private byte edad; //Lo mejor es la fecha de nacimiento

    private byte semestre;

    private String carrera;

    public Alumno(String numControl, String nombre, String primerAp, String segundoAp, byte edad, byte semestre, String carrera) {
        this.numControl = numControl;
        this.nombre = nombre;
        this.primerAp = primerAp;
        this.segundoAp = segundoAp;
        this.edad = edad;
        this.semestre = semestre;
        this.carrera = carrera;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public byte getEdad() {
        return edad;
    }

    public void setEdad(byte edad) {
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumControl() {
        return numControl;
    }

    public void setNumControl(String numControl) {
        this.numControl = numControl;
    }

    public String getPrimerAp() {
        return primerAp;
    }

    public void setPrimerAp(String primerAp) {
        this.primerAp = primerAp;
    }

    public String getSegundoAp() {
        return segundoAp;
    }

    public void setSegundoAp(String segundoAp) {
        this.segundoAp = segundoAp;
    }

    public byte getSemestre() {
        return semestre;
    }

    public void setSemestre(byte semestre) {
        this.semestre = semestre;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "numControl='" + numControl + '\'' +
                ", nombre='" + nombre + '\'' +
                ", primerAp='" + primerAp + '\'' +
                ", segundoAp='" + segundoAp + '\'' +
                ", edad=" + edad +
                ", semestre=" + semestre +
                ", carrera='" + carrera + '\'' +
                '}';
    }
}
