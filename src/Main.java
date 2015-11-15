public class Main
{
    public static void main(String[] args)
    {
//        Sample Data: CEO Bill has 3 employees reporting to him: {Dom, Samir, Michael}
//        Dom has three reports { Peter, Bob, Porter}
//        Samir has no reports {}
//        Michael has no reports {}
//        Peter has 2 reports {Milton, Nina}
//        Bob has no reports {}
//        Porter has no reports {}
//        Milton has no reports {}
//        Nina has no reports {}
//        Sample calls: closestCommonManager(Milton, Nina) = Peter
//        closestCommonManager(Nina, Porter) = Dom
//        closestCommonManager(Nina, Samir) = Bill
//        closestCommonManager(Peter, Nina) = Peter

        Company.Employee bill = new Company.Employee(0, "Bill");
        Company.Employee dom = new Company.Employee(1, "Dom");
        Company.Employee samir = new Company.Employee(2, "Samir");
        Company.Employee michael = new Company.Employee(3, "Michael");
        Company.Employee peter = new Company.Employee(4, "Peter");
        Company.Employee bob = new Company.Employee(5, "Bob");
        Company.Employee porter = new Company.Employee(6, "Porter");
        Company.Employee milton = new Company.Employee(7, "Milton");
        Company.Employee nina = new Company.Employee(8, "Nina");

        bill.addReport(dom);
        bill.addReport(samir);
        bill.addReport(michael);

        dom.addReport(peter);
        dom.addReport(bob);
        dom.addReport(porter);

        peter.addReport(milton);
        peter.addReport(nina);

        System.out.println("(Milton, Nina)->" + Company.closestCommonManager(bill, milton, nina).getName() );
        System.out.println("(Nina, Porter)->" + Company.closestCommonManager(bill, nina, porter).getName() );
        System.out.println("(Milton, Samir)->" + Company.closestCommonManager(bill, milton, samir).getName() );
        System.out.println("(Peter, Nina)->" + Company.closestCommonManager(bill, peter, nina).getName() );
    }
}
