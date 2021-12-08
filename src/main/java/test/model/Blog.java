package test.model;

public class Blog {
    private int id;
    private String name;
    private String content;
    private int idCategory;

    public Blog() {
    }

    public Blog(int id, String name, String content, int idCategory) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.idCategory = idCategory;
    }

    public Blog(String name, String content, int idCategory) {
        this.name = name;
        this.content = content;
        this.idCategory = idCategory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }
}
