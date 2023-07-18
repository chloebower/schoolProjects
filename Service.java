public class Service {
    private String serviceCar;
    private double serviceCost; 
    private int serviceTime;
    private double serviceFilter;
    private double serviceOil;

    public Service(){
        //default constructor
    }

    //declare service object
    public Service(String car, double cost, int time, double filter, double oil) {
        this.serviceCar = car;
        this.serviceCost = cost;
        this.serviceTime = time;
        this.serviceFilter = filter;
        this.serviceOil = oil;
    }

    //get and set methods

    public String getServiceCar(){
        return serviceCar;
    }
    public void setServiceCar(String car){
        this.serviceCar = car;
    }

    public double getServiceCost(){
        return serviceCost;
    }
    public void setServiceCost(double cost){
        this.serviceCost = cost;
    }

    public int getServiceTime(){
        return serviceTime;
    }
    public void setServiceTime(int time){
        this.serviceTime = time;
    }

    public double getServiceFilter(){
        return serviceFilter;
    }
    public void setServiceFilter(double filter){
        this.serviceFilter = filter;
    }

    public double getServiceOil(){
        return serviceOil;
    }
    public void setServiceOil(double oil){
        this.serviceOil = oil;
    }

    //intitialise expenses variable
    private double expenses;

    //create calculate expenses method
    public double calculateExpenses() {
        return expenses = (serviceFilter + serviceOil);
    }

    public double getExpenses(){
        return expenses;
    }

    public void setExpenses(double expenses){
        this.expenses = expenses;
    }
}
