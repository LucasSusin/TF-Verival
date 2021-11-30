package com.bcopstein;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import com.bcopstein.casosDeUso.Politicas.ICalculoCustoViagem;
import com.bcopstein.casosDeUso.Politicas.CalculoCustoViagemBasico;
import com.bcopstein.casosDeUso.Servicos.ServicosPassageiro;
import com.bcopstein.entidades.Bairro;
import com.bcopstein.entidades.Roteiro;
import com.bcopstein.interfaces.Persistencia.RepositorioBairrosImplMem;
import com.bcopstein.interfaces.Persistencia.RepositorioPassageirosImplMem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ServicosPassageiroTest {
    private ServicosPassageiro servicopassageiro;
    private RepositorioPassageirosImplMem passageiros;
    private RepositorioBairrosImplMem bairros;

    @BeforeEach
    public void setup() {
        ICalculoCustoViagem ccv = mock(CalculoCustoViagemBasico.class);

        passageiros = new RepositorioPassageirosImplMem();
        bairros = new RepositorioBairrosImplMem();
        servicopassageiro = new ServicosPassageiro(bairros, passageiros, ccv);
    }

    @Test
    public void testCriaRoteiro() {
        Bairro origem = bairros.recuperaPorNome("Bom Fim");
        Bairro destino = bairros.recuperaPorNome("Petropolis");
        Roteiro roteiro = new Roteiro(origem, destino, bairros.recuperaListaBairros());
        Roteiro roteiroTest = servicopassageiro.criaRoteiro(origem.getNome(), destino.getNome());
        assertTrue(roteiro.equals(roteiroTest));
    }
}