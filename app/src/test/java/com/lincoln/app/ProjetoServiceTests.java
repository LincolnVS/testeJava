package com.lincoln.app;

import com.lincoln.app.repository.ProjetoRepository;
import com.lincoln.app.dto.ProjetoDTO;
import com.lincoln.app.model.*;
import com.lincoln.app.enumeration.*;
import com.lincoln.app.service.*;

import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProjetoServiceTests {
    @InjectMocks
    private ProjetoService projetoService;
    @Mock
    private ProjetoRepository projetoRepository;
    private Optional<Projeto> projetoOptional;
    private Projeto projeto;
    private ProjetoDTO projetoDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        try {
            mockProjeto();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void buscarProjeto() {
        when(projetoRepository.findById(anyLong())).thenReturn(projetoOptional);
        Optional<Projeto> response = projetoService.buscarProjeto(1L);
        Assertions.assertEquals(projetoOptional, response);
    }

    @Test
    void buscarTodosProjetos() {
        when(projetoRepository.findAll()).thenReturn(List.of(projeto));
        List<Projeto> response = projetoService.buscarTodosProjetos();
        Assertions.assertEquals(1, response.size());
    }

    @Test
    void adicionarProjeto() {
        when(projetoRepository.save(any())).thenReturn(projeto);
        Projeto response = projetoService.adicionarProjeto(projeto);
        Assertions.assertEquals(projeto, response);
    }

    @Test
    void existeProjetoCadastrado() {
        when(projetoRepository.existsById(any())).thenReturn(true);
        boolean response = projetoService.existeProjetoCadastrado(2L);
        Assertions.assertEquals(true, response);
    }

    @Test
    void atualizarProjeto() {
        when(projetoRepository.save(any())).thenReturn(projeto);
        Projeto response = projetoService.atualizarProjeto(projeto);
        Assertions.assertEquals(projeto, response);
    }

    @Test
    void deletarProjeto() {
        doNothing().when(projetoRepository).deleteById(anyLong());
        projetoService.deletarProjeto(2L);
        verify(projetoRepository, times(1)).deleteById(anyLong());
    }

    public void mockProjeto() throws ParseException {
        List<Pessoa> membros = new ArrayList<Pessoa>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        membros.add(new Pessoa(1L, "Lincoln", dateFormat.parse("1997-01-01"), "", false));

        projetoDTO = new ProjetoDTO(
                Optional.of(1L),
                "Projeto 0",
                dateFormat.parse("1995-01-01"),
                dateFormat.parse("1995-01-01"),
                dateFormat.parse("1995-01-01"),
                5000.0F,
                "Descrição projeto",
                StatusProjeto.iniciado,
                RiscoProjeto.alto,
                membros
        );
        projetoOptional = Optional.of(new Projeto(
                1L,
                "Projeto 0",
                dateFormat.parse("1995-01-01"),
                dateFormat.parse("1995-01-01"),
                dateFormat.parse("1995-01-01"),
                "Descrição projeto",
                StatusProjeto.iniciado,
                5000.0F,
                RiscoProjeto.alto,
                membros
        ));
        projeto = new Projeto(
                1L,
                "Projeto 0",
                dateFormat.parse("1995-01-01"),
                dateFormat.parse("1995-01-01"),
                dateFormat.parse("1995-01-01"),
                "Descrição projeto",
                StatusProjeto.iniciado,
                5000.0F,
                RiscoProjeto.alto,
                membros
        );
    }
}
