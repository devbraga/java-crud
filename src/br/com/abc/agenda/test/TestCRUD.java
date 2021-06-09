package br.com.abc.agenda.test;

import br.com.abc.agenda.dao.ContatoDAO;
import br.com.abc.agenda.model.Contato;

import java.util.Date;
import java.util.List;

public class TestCRUD {

    public static void main(String[] args) {
        ContatoDAO contatoDAO = new ContatoDAO();

        Contato contato = new Contato("Gabriel", 23, new Date());
        Contato contato1 = new Contato(4,"Manuela", 20, new Date());
        //contatoDAO.save(contato);
        //contatoDAO.update(contato1);
        //contatoDAO.deleteById(4);
        //contatoDAO.listByName("a");

//       for (Contato c : contatoDAO.listAll()) {
//           System.out.println("Contato: "+ c.getNome() + " " + c.getIdade() + " anos "+ c.getDataCadastro());
//       }

        for (Contato c : contatoDAO.listByName("a")) {
           System.out.println("Contato: "+ c.getNome() + " " + c.getIdade() + " anos "+ c.getDataCadastro());
       }
    }

}
