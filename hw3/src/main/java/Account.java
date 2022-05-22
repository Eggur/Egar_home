public class Account  {
    private String id;
    private String employee;

    <T>Account(T id, T employee){
        this.id = id.toString();
        this.employee = employee.toString();
    }



    public String getId() {
        return id;
    }




    public String getEmployee() {
        return employee;
    }


    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", employee='" + employee + '\'' +
                '}';
    }
}
