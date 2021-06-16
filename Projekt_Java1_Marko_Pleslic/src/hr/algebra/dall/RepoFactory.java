/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dall;

import hr.algebra.dal.sql.SqlRepository;

/**
 *
 * @author Marko
 */
public class RepoFactory {
    private RepoFactory() {
    }
    
    public static Repository getRepository() throws Exception {
        return new SqlRepository();
    }
}
