package com.rogerioreis.anuncio02.servicos;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rogerioreis.anuncio02.dto.UsuarioDto;
import com.rogerioreis.anuncio02.entidades.Usuario;
import com.rogerioreis.anuncio02.repositorios.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private ModelMapper mapper;

	public List<UsuarioDto> ListarUsuarios() {
		
		List<Usuario> listBanco = repository.findAll();	
		
		List<UsuarioDto> listDto= new ArrayList<>();
		UsuarioDto dto = new UsuarioDto();
		for (Usuario usuario : listBanco) {
			
			listDto.add(mapper.map(usuario,dto.getClass()));
		} 
		return listDto;
	}

	public UsuarioDto buscarPorId(Long id) {
		Usuario usuario = repository.findById(id).get();
		UsuarioDto dto = new UsuarioDto(); 
		return mapper.map(usuario, dto.getClass());
	}

	public UsuarioDto inserirUsuario(Usuario usuario) {
		Usuario obj = new Usuario();
		obj.setNome(usuario.getNome());
		obj.setEmail(usuario.getEmail());
		obj.setTelefone(usuario.getTelefone());
		obj.setSenha(passwordEncoder.encode(usuario.getSenha()));
		obj.setPerfil(usuario.getPerfil());
		obj.setStatus(usuario.getStatus());
		obj.setDtCadastro(usuario.getDtCadastro());
		
		repository.save(obj);
		return new UsuarioDto(usuario);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
}








