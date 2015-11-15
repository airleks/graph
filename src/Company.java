import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class Company {

    // IMPORTANT: DO NOT MODIFY THIS CLASS
    public static class Employee {

        private final int id;
        private final String name;
        private List<Employee> reports;

        public Employee(int id, String name) {
            this.id = id;
            this.name = name;
            this.reports = new ArrayList<Employee>();
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public List<Employee> getReports() {
            return reports;
        }

        public void addReport(Employee employee) {
            reports.add(employee);
        }
    }

    /*
     * Read the attached PDF for more explanation about the problem
     * Note: Don't modify the signature of this function
     * @param ceo
     *
     * @param firstEmployee
     *            
     * @param secondEmployee
     *            
     * @return common manager for both employees that is closest to them.
     */
    @SuppressWarnings("unused")
    public static Employee closestCommonManager(Employee ceo, Employee firstEmployee, Employee secondEmployee)
    {
        class WorkerJob extends RecursiveTask<Employee>
        {
            Employee current;
            Employee firstEmployee;
            Employee secondEmployee;

            public WorkerJob(Employee current, Employee firstEmployee, Employee secondEmployee)
            {
                this.current = current;
                this.firstEmployee = firstEmployee;
                this.secondEmployee = secondEmployee;
            }

            @Override
            protected Employee compute() {
                Employee result = current.getId() == firstEmployee.getId() ? firstEmployee :
                                    current.getId() == secondEmployee.getId() ? secondEmployee :
                                            null;

                if (result == null)
                {
                    result =
                            current.getReports().stream().map(r -> new WorkerJob(r, firstEmployee, secondEmployee).fork())
                            .map(ForkJoinTask::join)
                                    .reduce(null, (f, s) -> (f != null) && (s != null) ? current :
                                            f != null ? f : s != null ? s : null);
                }

                return result;
            }
        }

        int maxThreads = Runtime.getRuntime().availableProcessors() * 2;
        ForkJoinPool pool = new ForkJoinPool(maxThreads);

        return pool.invoke(new WorkerJob(ceo, firstEmployee, secondEmployee));
    }
};