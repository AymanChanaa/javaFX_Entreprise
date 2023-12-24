package com.example.javafx_employe.Metiers;

import java.util.List;
import java.util.Optional;

public class Test {
    public static void main(String[] args) {
        Departement dep = new Departement(5,"Commerce");
        DaoDepartement DaoDep = new DaoDepartement();
        /*List<Departement> deps1 = DaoDep.All();
        deps1.forEach(departement -> System.out.println(departement));
        DaoDep.Create(dep);
        List<Departement> deps2 = DaoDep.All();
        deps2.forEach(departement -> System.out.println(departement));*/
        /*Optional<Departement> dep2 = DaoDep.Read(2);
        dep2.ifPresentOrElse(
            departement -> System.out.println(departement),
                () -> System.out.println("Departement not Found")
        );*/
        /*Departement dep3 = new Departement(7,"Finance");
        DaoDep.Update(dep3,5);*/
        /*List<Departement> deps3 = DaoDep.All();
        deps3.forEach(departement -> System.out.println(departement));*/
        /*DaoDep.Delete(5);
        List<Departement> deps3 = DaoDep.All();
        deps3.forEach(departement -> System.out.println(departement));*/
        System.out.println("Nbre de departements: " + DaoDep.Count());

    }
}
