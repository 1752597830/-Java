package test.dao;

import test.pojo.Emp;

import java.util.List;

/**
 * @Auther: qinfeng
 * @Date: 2021/5/23 - 05 - 23 - 14:03
 * @Description: test.dao
 */
public interface EmpDao {
    int EmpAdd(Emp emp);
    int EmpDelByempno(int empno);
    int EmpUpdateByempno(int empno,Emp emp);
    List<Emp> EmpQueryBydeptno(int deptno);
    List<Emp> EmpFindAll();
}
