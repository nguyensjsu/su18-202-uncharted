package com.uncharted;

public class EmployeeDAOFactory {
    public static EmployeeDAO getInstance()
    {
        return new EmployeeDAOImpl();
    }
}
