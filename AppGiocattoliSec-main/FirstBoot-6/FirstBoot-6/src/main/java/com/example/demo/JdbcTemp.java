package com.example.demo;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

/*JdbcTemplate è una classe di Spring per interagire con il database in modo semplificato.
Fornisce metodi come update per query INSERT, DELETE, UPDATE e query per le SELECT.
JdbcTemplate si basa sulle configurazioni di connessione al database specificate nel file di configurazione Spring.*/


//grazie a questo è possibile  utilizzare l'iniezione delle dipendenze con @Autowired

@Component
public class JdbcTemp {

    // Oggetto JdbcTemplate per eseguire query e update sul database.
    private JdbcTemplate jdbcTemplateObject;

    /*
     * Metodo per iniettare l'istanza di JdbcTemplate nella classe.
     * @Autowired indica a Spring di fornire automaticamente un'istanza di JdbcTemplate.
     * Autowired in Spring indica che un’istanza di JdbcTemplate deve essere iniettata automaticamente.
      In questo caso, Spring creerà automaticamente un'istanza di JdbcTemplate (se è configurato correttamente)
     e la fornirà al setter setJdbcTemplateObject.
     */
    ///insranzio un jdbctemplateoobject  grazie alla dipendence iniection lo faccio una sola volta usando autowuered
    //questo oggetto è in grado di fare le query
    @Autowired
    public void setJdbcTemplateObject(JdbcTemplate jdbcTemplateObject) {
        this.jdbcTemplateObject = jdbcTemplateObject;
        
    }

    /*
     * Metodo per inserire un nuovo prodotto nel database.
     * Prende in input i valori necessari per la tabella e restituisce un intero,
     * che indica il numero di righe modificate (inserite).
     */
    public int insertGame(String nome, String marca, double prezzo, String url) {
        String query = "INSERT INTO giocattoli (nome, marca, prezzo, url, pezzi, pezziV) VALUES (?, ?, ?, ?,?,?)";
        return jdbcTemplateObject.update(query, nome, marca,  prezzo, url, 50, 0);
        //jdbcTemplateObject tramite update si prende la query e i paramentri e va a fare l' update. ovvero lo fa tramite un preparedStatement
        //si fa il return perche update ritorna un rows ovvero le righe cambiate .. possiamo utilizzarlo per sapere quante righe sono cambiate per esempio.
        
    }

    /*
     * Metodo per eliminare un pc dal database in base al nome.
     * Restituisce il numero di righe eliminate.
     */
    public int delete(String nome) {
        String query = "DELETE FROM giocattoli WHERE nome = ?";
        return jdbcTemplateObject.update(query, nome);
    }
    /*
     * 
     * Metodo di update per cambiare il prezzo a partire dal nome
     */
    public int setPrezzo(String nome, double prezzo) {
    	
    	String query = "UPDATE giocattoli SET prezzo = (?) WHERE nome = (?)";
    	return jdbcTemplateObject.update(query, prezzo, nome);
    }
    
    public int change(String nome, int pezzi) {
    	
    	String query = "UPDATE giocattoli SET pezzi = pezzi - (?) WHERE nome = (?)";
    	jdbcTemplateObject.update(query, pezzi, nome);
    	String query1 = "UPDATE giocattoli SET pezziV = pezziV + (?) WHERE nome = (?)";
    	return jdbcTemplateObject.update(query1, pezzi, nome);
    	
    	
    }
    
    /*
     * Metodo per ottenere una lista di tutti i pc presenti nel database.
     * Utilizza un ResultSetExtractor per convertire il ResultSet in un ArrayList di oggetti pc.
     */
    
    /*
     * qui uso select come query a cui passo oggetto resultsetextractor
     * questa è una interfaccia che è una classe con metodi astratti. ovvero possiamo dire che è un tipo di arraylist di pc
     * una classe astratta puo avere metodi astratti e non , una interfaccia ha tutti i metodi astratti ovvero è generica
     * ed è utilizzata per personalizzare delle funzioni. in questo caso resultextractor avra arraylist di tipo pc ma potrebbe esserci
     * un vector di integer o altro o addirittura nulla.
     * se mettessi ResultSet dovrei per forza mettere arrayList<pc>
     *
     * */
    //qui ritorna un arraylist di pc ..(NOTALO dopo public)
    
    public ArrayList<gioco> getLista() {
        String query = "SELECT * FROM giocattoli";

        return jdbcTemplateObject.query(query, new ResultSetExtractor<ArrayList<gioco>>() {
        /*
         * usando resultSetExtractor posso personalizzare i dati che voglio ottenere 
         * mi crea questo public ...throws per implementare i metodi di extractData
         * 
         * il metododo extract data ha come risultato un resulset ecco perche esce li
         * */
        	@Override
            public ArrayList<gioco> extractData(ResultSet rs) throws SQLException, DataAccessException {
                ArrayList<gioco> listaP = new ArrayList<>();

                // Itera sui risultati della query e crea un nuovo oggetto pc per ciascun record.
                while (rs.next()) {
                    gioco p1 = new gioco();
                    p1.setNome(rs.getString("nome"));
                    p1.setMarca(rs.getString("marca"));
                   
                    p1.setPrezzo(rs.getDouble("prezzo"));
                    p1.setUrl(rs.getString("url"));
                    p1.setPezzi(rs.getInt("pezzi"));
                    p1.setPezziV(rs.getInt("pezziV"));
                    

                    listaP.add(p1);
                }

                return listaP;
            }
        });
    }
}

///nel controller se voglioamo ottenere la lista dei dati basta assegnare alla lista il risultato della quiery getLista
///lo chiamiamo sul pcJdbcTemplate che è iniettato nel controller con autowierd  nel costruttore (non con il set)
//e quindi non serve scriverlo altrove .ed l oggetto jbdctemplate è gia configurato a livello di connessione tramite la classe DatabaseConfig 

