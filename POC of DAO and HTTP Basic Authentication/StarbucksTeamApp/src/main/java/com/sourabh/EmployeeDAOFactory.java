package com.sourabh;

public class EmployeeDAOFactory {
    public static EmployeeDAO getInstance()
    {
        return new EmployeeDAOImpl();
    }
}
