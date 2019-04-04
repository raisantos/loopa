package com.loopa.api;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.loopa.api.irepository.IAtendimentoRepository;
import com.loopa.api.irepository.IAvaliacaoRepository;
import com.loopa.api.irepository.IClienteRepository;
import com.loopa.api.irepository.IProfissionalRepository;
import com.loopa.api.irepository.IServicoRepository;
import com.loopa.api.irepository.IUsuarioRepository;
import com.loopa.api.model.Atendimento;
import com.loopa.api.model.Avaliacao;
import com.loopa.api.model.Cliente;
import com.loopa.api.model.Profissional;
import com.loopa.api.model.Servico;
import com.loopa.api.model.Usuario;

@SpringBootApplication
public class LoopaApplication implements CommandLineRunner{

	@Autowired
	IClienteRepository clienteRepository;
	
	@Autowired
	IProfissionalRepository profissionalRepository;
	
	@Autowired
	IServicoRepository servicoRepository;
	
	@Autowired
	IUsuarioRepository usuarioRepository;
	
	@Autowired
	IAvaliacaoRepository avaliacaoRepository;
	
	@Autowired
	IAtendimentoRepository atendimentoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(LoopaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Servico servico = new Servico();
		servico.setDescricao("encanador");
		servicoRepository.save(servico);
		
		Servico servico2 = new Servico();
		servico2.setDescricao("mecanico");
		servicoRepository.save(servico2);
		
		Profissional p = new Profissional();
		p.setEmail("rai@gmail");
		p.setNome("Rai");
		p.setSenha("123");
		p.setLatitude(-0.32015);
		p.setLongitude(0.1546);
		p.setServico(servico2);
		profissionalRepository.save(p);
		
		Cliente c = new Cliente();
		c.setEmail("jailson@gmail");
		c.setNome("Jailson");
		c.setSenha("123");
		c.setLatitude(-0.32015);
		c.setLongitude(0.1546);
		clienteRepository.save(c);
		
		Avaliacao a = new Avaliacao();
		a.setCliente(c);
		a.setComentario("muito bom");
		a.setNota(5);
		a.setProfissional(p);
		avaliacaoRepository.save(a);
		
		Atendimento at = new Atendimento();
		at.setCliente(c);
		at.setData(new Date());
		at.setProfissional(p);
		at.setLongitudeCliente(c.getLongitude());
		at.setLatitudeCliente(c.getLatitude());
		atendimentoRepository.save(at);
	}
}
