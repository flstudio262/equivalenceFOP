package com.testHibernate.service.listePromotion;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testHibernate.converts.listePromotion.ListePromotionFormToListePromotion;
import com.testHibernate.model.listePromotion.ListePromotion;
import com.testHibernate.model.listePromotion.ListePromotionForm;
import com.testHibernate.repo.listePromotion.ListePromotionRepository;
 
@Service
public class ListePromotionServiceImpl implements ListePromotionService {
	
    private ListePromotionRepository listePromotionRepository;
    private ListePromotionFormToListePromotion listePromotionFormTolistePromotion;
    
    @PersistenceContext
    private EntityManager em;
    
    @Autowired
    public ListePromotionServiceImpl(ListePromotionRepository listePromotionRepository,
			ListePromotionFormToListePromotion listePromotionFormTolistePromotion) {
		super();
		this.listePromotionRepository = listePromotionRepository;
		this.listePromotionFormTolistePromotion = listePromotionFormTolistePromotion;
	}

	@Override
    public List<ListePromotion> listAll() {
       List<ListePromotion> ret = new ArrayList<>();
       listePromotionRepository.findAll().forEach(ret::add); //fun with Java 8
       return ret;
    }

	@Override
    public ListePromotion getById(Long id) {
        return this.listePromotionRepository.findById(id).orElse(null);
    }

	@Override
	public void delete(Long id) {
		this.listePromotionRepository.deleteById(id);
	}

	@Override
	public List<ListePromotion> getBySession(String session) {
		TypedQuery<ListePromotion> query = em.createNamedQuery("listePromotion.findBySession", ListePromotion.class).setParameter("session", session);
		List<ListePromotion> ret = query.getResultList();
		
		return ret;
	}

	@Override
	public ListePromotion saveOrUpdate(ListePromotion listePromotion) {
		listePromotionRepository.save(listePromotion);
        return listePromotion;
	}

	@Override
	public ListePromotion saveOrUpdateListePromotionForm(ListePromotionForm listePromotionForm) {
		ListePromotion savedlistePromotion = saveOrUpdate(listePromotionFormTolistePromotion.convert(listePromotionForm));

        System.out.println("Saved ArreteEqRef Id: " + savedlistePromotion.getId());  
        return savedlistePromotion;
	}

}