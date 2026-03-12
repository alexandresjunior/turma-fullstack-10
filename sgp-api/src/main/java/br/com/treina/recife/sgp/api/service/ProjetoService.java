package br.com.treina.recife.sgp.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.treina.recife.sgp.api.model.Projeto;
import br.com.treina.recife.sgp.api.repository.ProjetoRepository;

@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository projetoRepository;

    public List<Projeto> listarProjetos() {
        return projetoRepository.findAll();
    }

    public Optional<Projeto> obterDadosDoProjeto(Long id) {
        return projetoRepository.findById(id);
    }

    public Projeto cadastrarProjeto(Projeto projeto) {
        return projetoRepository.save(projeto);
    }

    public Projeto atualizarProjeto(Long id, Projeto projeto) {
        projeto.setId(id);
        return projetoRepository.save(projeto);
    }

    public void excluirProjeto(Long id) {
        projetoRepository.deleteById(id);
    }

    // SELECT * FROM TB_PROJETOS WHERE usuario_resp_id = ?
    public List<Projeto> listarProjetosDeUmUsuario(Long idUsuario) {
        return projetoRepository.findByResponsavel_Id(idUsuario);
    }

}
