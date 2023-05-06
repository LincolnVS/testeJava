package com.lincoln.app;

import com.lincoln.app.dto.PessoaDTO;
import com.lincoln.app.model.Pessoa;
import com.lincoln.app.repository.PessoaRepository;
import com.lincoln.app.service.PessoaService;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
class PessoaServiceTests {
    @InjectMocks
    private PessoaService pessoaService;
    @Mock
    private PessoaRepository pessoaRepository;
    private Optional<Pessoa> pessoaOptional;
    private Pessoa pessoa;
    private PessoaDTO pessoaDTO;

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
        when(pessoaRepository.findById(anyLong())).thenReturn(pessoaOptional);
        Optional<Pessoa> response = pessoaService.buscarPessoa(1L);
        Assertions.assertEquals(pessoaOptional, response);
    }

    @Test
    void buscarTodosProjetos() {
        when(pessoaRepository.findAll()).thenReturn(List.of(pessoa));
        List<Pessoa> response = pessoaService.buscarTodasPessoas();
        Assertions.assertEquals(1, response.size());
    }

    @Test
    void adicionarProjeto() {
        when(pessoaRepository.save(any())).thenReturn(pessoa);
        Pessoa response = pessoaService.adicionarPessoa(pessoa);
        Assertions.assertEquals(pessoa, response);
    }

    @Test
    void existeProjetoCadastrado() {
        when(pessoaRepository.existsById(any())).thenReturn(true);
        boolean response = pessoaService.existePessoaCadastrada(2L);
        Assertions.assertEquals(true, response);
    }

    @Test
    void atualizarProjeto() {
        when(pessoaRepository.save(any())).thenReturn(pessoa);
        Pessoa response = pessoaService.atualizarPessoa(pessoa);
        Assertions.assertEquals(pessoa, response);
    }

    @Test
    void deletarProjeto() {
        doNothing().when(pessoaRepository).deleteById(anyLong());
        pessoaService.deletarPessoa(2L);
        verify(pessoaRepository, times(1)).deleteById(anyLong());
    }

    public void mockProjeto() throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        pessoaDTO = new PessoaDTO(
                Optional.of(1L),
                "Lincoln",
                dateFormat.parse("1997-01-01"),
                "",
                false);
        pessoaOptional = Optional.of(new Pessoa(
                1L,
                "Lincoln",
                dateFormat.parse("1997-01-01"),
                "",
                false)
        );
        pessoa = new Pessoa(
                1L,
                "Lincoln",
                dateFormat.parse("1997-01-01"),
                "",
                false
        );
    }
}
