package com.ifes.devweb.service;

import com.ifes.devweb.dto.LocacaoDTO;
import com.ifes.devweb.model.Locacao;
import com.ifes.devweb.repository.LocacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocacaoService {
    private final LocacaoRepository locacaoRepository;

    public void salvarLocacao(LocacaoDTO dto) {
        Locacao locacao = new Locacao();
        locacao.setDtLocacao(dto.dtLocacao());

        locacao.setDtDevolucaoEfetiva(null);
    }
}
