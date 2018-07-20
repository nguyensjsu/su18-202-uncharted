package com.sourabh;

public interface EmployeeDAO
{
    int insertEmployee(EmployeeBO bo) throws Exception;
    EmployeeBO getEmployee(int id) throws Exception;
}
