public class User {
    private String name;
    private String book;
    private String amount;



    <T>User(T name, T book, T amount) {
        this.name = name.toString();
        this.book = book.toString();
        this.amount = amount.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", book='" + book + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }


}
