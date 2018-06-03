package Model;

public class MarcaVeiculo
{
    private int id;
    private String marca;

    @Override
    public String toString() {
        return "MarcaVeiculo{" +
                "id=" + id +
                ", marca='" + marca + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
}
