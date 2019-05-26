/*
 * 
 * 
 * 
 */
package api.persistence.services;

import api.persistence.entity.Socios;
import api.persistence.repository.SociosRepository;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Carlos Cunha
 */
@Service
public class SociosService implements Serializable {

    @Autowired
    private SociosRepository repository;

    public Socios getById(Long id) throws Exception {
        return repository.findById(id).get();
    }

    public Set<Socios> getAll() throws Exception {
        return new HashSet<>(repository.findAll());
    }

    public Socios save(Socios pessoa) throws Exception {
        return repository.save(pessoa);
    }

    public Set<Socios> getPaginated(int page, int elements) throws Exception {
        return new HashSet<>(repository.findAll(PageRequest.of(page, elements)).getContent());
    }

    @Transactional(readOnly = false)
    public Socios edit(Socios pessoa, Long id) throws Exception {
        Socios oldPessoa = this.getById(id);
        oldPessoa.setEmpresa(pessoa.getEmpresa());
        oldPessoa.setPessoa(pessoa.getPessoa());
        oldPessoa.setValorDaCota(pessoa.getValorDaCota());

        return oldPessoa;

    }

    public void delete(Long id) throws Exception {
        repository.deleteById(id);
    }
}
