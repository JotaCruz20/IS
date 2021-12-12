package beans;

import data.Admin;
import data.Client;
import data.Currencies;
import data.Totals;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class Bean{
    @PersistenceContext(unitName = "projeto")
    EntityManager em;

    public void addClient(Client client){
        em.persist(client);
    }

    public void addManager(Admin admin){
        em.persist(admin);
    }

    public void addCurrency(Currencies currencies){
        em.persist(currencies);
    }

    public List<Client> getClient(){
        TypedQuery<Client> q = em.createQuery("from Client c ", Client.class);
        return q.getResultList();
    }

    public List<Admin> getAdmin(){
        TypedQuery<Admin> q = em.createQuery("from Admin a ", Admin.class);
        return q.getResultList();
    }

    public List<Currencies> getCurrencies(){
        TypedQuery<Currencies> q = em.createQuery("from Currencies c ", Currencies.class);
        return q.getResultList();
    }

    public Admin login(String name){
        try {
            TypedQuery<Admin> q = em.createQuery("from Admin a " +
                    "where a.name = :name", Admin.class).setParameter("name", name);
            return q.getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    public Totals getTotals(){
        TypedQuery<Totals> q = em.createQuery("from Totals t where t.id = 1", Totals.class);
        return q.getSingleResult();
    }

    public List<Client> getClientNoPayments(){
        TypedQuery<Client> q = em.createQuery("from Client c where c.numOfPayments=0", Client.class);
        return q.getResultList();
    }
}