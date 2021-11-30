package com.bcopstein;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.bcopstein.entidades.Bairro;
import com.bcopstein.entidades.geometria.Area;
import com.bcopstein.entidades.geometria.Ponto;
import com.bcopstein.entidades.geometria.Reta;
import com.bcopstein.entidades.geometria.SituacaoReta;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BairroTest {
    private Area a1,a2,a3,a4;
    private Reta reta, reta1;

    @BeforeEach
    public void setup(){
        reta = new Reta(new Ponto(10,10),new Ponto(20,10));
        reta1 = new Reta(new Ponto(10,10),new Ponto(20,20));
        a1 = mock(Area.class);
        when(a1.classifica(reta)).thenReturn(SituacaoReta.TODA_DENTRO);

        a2 = mock(Area.class);
        when(a2.classifica(reta)).thenReturn(SituacaoReta.TODA_FORA);
        when(a2.classifica(reta1)).thenReturn(SituacaoReta.INTERSECTA);
        when(a2.pontoCentral()).thenReturn(new Ponto(20,20));

        a3 = mock(Area.class);
        when(a3.getPInfDir()).thenReturn(new Ponto(3, 2));
        when(a3.getPSupEsq()).thenReturn(new Ponto(1, 4));

        a4 = mock(Area.class);
        when(a4.getPInfDir()).thenReturn(new Ponto(5, 2));
        when(a4.getPSupEsq()).thenReturn(new Ponto(1, 4));
    }

    @Test
    public void testaAlteraCustoTransporte(){
        Bairro bairro = new Bairro("Auxiliadora",a2,10);
        bairro.alteraCustoTransporte(20);
        assertEquals(bairro.getCustoTransporte(), 20);
    }

    @Test
    public void testaEquals(){
        Bairro bairro = new Bairro("Auxiliadora",a2,10);

        Bairro bairroIgual = new Bairro("Auxiliadora",a2,10);
        Bairro bairroDiferenteNome = new Bairro("Auxiliadora2",a2,10);
        Bairro bairroDiferenteArea = new Bairro("Auxiliadora",a1,10);
        Bairro bairroDiferenteCusto = new Bairro("Auxiliadora",a2,20);
        assertTrue(bairro.equals(bairroIgual));
        assertFalse(bairro.equals(bairroDiferenteNome));
        assertFalse(bairro.equals(bairroDiferenteArea));
        assertFalse(bairro.equals(bairroDiferenteCusto));
    }


    @Test
    public void testaPontoCentral(){
        Bairro bairro = new Bairro("Auxiliadora",a2,10);
        Ponto ptCentralEsperado = new Ponto(20,20);
        Ponto ptCentralObservado = bairro.getPontoCentral();
        assertEquals(ptCentralEsperado, ptCentralObservado);
    }

    @Test
    public void testaClassificaReta(){
        Bairro bairro = new Bairro("Auxiliadora",a2,10);
        SituacaoReta sitEsp = SituacaoReta.INTERSECTA;
        SituacaoReta sitObs = bairro.getClassificacao(reta1);
        assertEquals(sitEsp, sitObs);
    }
}
