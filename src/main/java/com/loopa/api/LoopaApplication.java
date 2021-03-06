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
		p.setEndereco("");
		p.setTelefone("");
		p.setStatus("ativo");
		profissionalRepository.save(p);
		
		for(long i = 2; i < 20; i++) {
			Profissional p1 = new Profissional();
			p1.setId(i);
			p1.setEmail("rai@gmail");
			p1.setNome("Rai");
			p1.setSenha("123");
			p1.setLatitude(-0.32015);
			p1.setLongitude(0.1546);
			p1.setServico(servico2);
			p1.setEndereco("");
			p1.setTelefone("");
			p1.setStatus("ativo");
			profissionalRepository.save(p1);
		}
		
		Cliente c = new Cliente();
		c.setEmail("jailson@gmail");
		c.setNome("Jailson");
		c.setSenha("123");
		c.setLatitude(-0.32015);
		c.setLongitude(0.1546);
		clienteRepository.save(c);
		
		for(long i = 2; i < 10; i++) {
			Cliente c1 = new Cliente();
			c1.setId(i);
			c1.setEmail("jailson@gmail");
			c1.setNome("Jailson");
			c1.setSenha("123");
			c1.setLatitude(-0.32015);
			c1.setLongitude(0.1546);
			clienteRepository.save(c1);
		}
		
		Avaliacao a = new Avaliacao();
		a.setCliente(c);
		a.setComentario("muito bom");
		a.setNota(5);
		a.setProfissional(profissionalRepository.getOne((long) 2));
		avaliacaoRepository.save(a);
		
		for (long i=2; i< 10; i++) {
			Avaliacao a1 = new Avaliacao();
			a1.setId(i);
			a1.setCliente(clienteRepository.getOne(i));
			a1.setComentario("muito bom");
			a1.setNota(5);
			a1.setProfissional(profissionalRepository.getOne(i));
			avaliacaoRepository.save(a1);
		}
		
		Avaliacao a2 = new Avaliacao();
		a2.setCliente(clienteRepository.getOne((long) 1));
		a2.setComentario("muito bom");
		a2.setNota(5);
		a2.setProfissional(profissionalRepository.getOne((long) 3));
		avaliacaoRepository.save(a2);
		
		Atendimento at = new Atendimento();
		at.setCliente(c);
		at.setData(new Date());
		at.setProfissional(p);
		at.setLongitudeCliente(c.getLongitude());
		at.setLatitudeCliente(c.getLatitude());
		atendimentoRepository.save(at);
	}
}
