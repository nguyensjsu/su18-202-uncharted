package com.sourabh;

public class EmployeeService {

    public String registerNewEmployee(EmployeeBO bo)
    {
        try {
            EmployeeDAO dao = EmployeeDAOFactory.getInstance();
            if(dao.insertEmployee(bo))
                return "registration success";
            return "registration failed";
        }
        catch(Exception ex)
        {
            System.out.println(ex);
            return "registration failed";
        }
    }

    public EmployeeBO getEmployeeById(int id)
    {
        try {
            EmployeeDAO dao = EmployeeDAOFactory.getInstance();
            return dao.getEmployee(id);
        }
        catch(Exception ex)
        {
            System.out.println(ex);
            return null;
        }
    }
}
