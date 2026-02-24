# Criando APIs REST com Spring Boot

**REST (Representational State Transfer)** é um estilo de arquitetura para sistemas distribuídos. Ele foi introduzido por Roy Fielding em sua tese de doutorado em 2000 e é amplamente utilizado para desenvolver APIs na web devido à sua simplicidade e flexibilidade.

---

## Responsabilidades no REST

1. **Client-Server**: Separação de responsabilidades entre cliente e servidor.
2. **Stateless**: Cada requisição do cliente para o servidor deve conter todas as informações necessárias para entender e processar o pedido.
3. **Cacheable**: As respostas devem ser explicitamente rotuladas como cacheáveis ou não cacheáveis.
4. **Uniform Interface**: Uso de uma interface uniforme para interagir com o sistema.
5. **Layered System**: A arquitetura pode ser composta por camadas que são independentes umas das outras.
6. **Code on Demand (opcional)**: O servidor pode fornecer código executável ao cliente quando necessário.

---

## Requisições e Comunicações

- **Endpoint**: URL que representa o recurso na API.
- **Requisição**: Contém método HTTP, URL, cabeçalhos e, opcionalmente, um corpo de mensagem.
- **Resposta**: Contém código de status HTTP, cabeçalhos e, opcionalmente, um corpo de mensagem.

---

## Métodos HTTP

- **GET**: Recupera informações de um recurso.
  ```java
  @GetMapping("/api/usuarios/{id}")
  public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
      Usuario usuario = usuarioService.findById(id);
      return ResponseEntity.ok(usuario);
  }
  ```

- **POST**: Cria um novo recurso.
  ```java
  @PostMapping("/api/usuarios")
  public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
      Usuario createdUsuario = usuarioService.save(usuario);
      return ResponseEntity.status(HttpStatus.CREATED).body(createdUsuario);
  }
  ```

- **PUT**: Atualiza um recurso existente.
  ```java
  @PutMapping("/api/usuarios/{id}")
  public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuarioDetails) {
      Usuario updatedUsuario = usuarioService.update(id, usuarioDetails);
      return ResponseEntity.ok(updatedUsuario);
  }
  ```

- **DELETE**: Remove um recurso.
  ```java
  @DeleteMapping("/api/usuarios/{id}")
  public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
      usuarioService.delete(id);
      return ResponseEntity.noContent().build();
  }
  ```

---

## Códigos de Resposta

- **200 OK**: Requisição bem-sucedida.
- **201 Created**: Novo recurso criado.
- **204 No Content**: Requisição bem-sucedida, mas sem conteúdo para retornar.
- **400 Bad Request**: Requisição inválida.
- **401 Unauthorized**: Autenticação necessária.
- **403 Forbidden**: Acesso ao recurso não permitido.
- **404 Not Found**: Recurso não encontrado.
- **500 Internal Server Error**: Erro no servidor.

---

## Boas Práticas no Desenvolvimento de uma API REST com Spring Boot

1. **Estrutura de Pacotes**:
   - **Controller**: Responsável pelas requisições HTTP.
   - **Service**: Contém a lógica de negócios.
   - **Repository**: Comunicação com o banco de dados.

2. **Validação de Dados**:
   ```java
   @PostMapping("/api/usuarios")
   public ResponseEntity<Usuario> createUsuario(@Valid @RequestBody Usuario usuario) {
       Usuario createdUsuario = usuarioService.save(usuario);
       return ResponseEntity.status(HttpStatus.CREATED).body(createdUsuario);
   }
   ```

3. **Tratamento de Exceções**:
   ```java
   @ControllerAdvice
   public class GlobalExceptionHandler {
       @ExceptionHandler(ResourceNotFoundException.class)
       public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
           Map<String, String> errorDetails = new HashMap<>();
           errorDetails.put("message", ex.getMessage());
           return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
       }
   }
   ```

4. **Documentação**:
   - Utilize Swagger para documentar a API.
     ```java
     @Configuration
     @EnableSwagger2
     public class SwaggerConfig {
         @Bean
         public Docket api() {
             return new Docket(DocumentationType.SWAGGER_2)
                     .select()
                     .apis(RequestHandlerSelectors.basePackage("com.example.demo"))
                     .paths(PathSelectors.any())
                     .build();
         }
     }
     ```

5. **Autenticação e Autorização**:
   - Utilize Spring Security para gerenciar a segurança da API.
     ```java
     @Configuration
     @EnableWebSecurity
     public class SecurityConfig extends WebSecurityConfigurerAdapter {
         @Override
         protected void configure(HttpSecurity http) throws Exception {
             http.csrf().disable()
                 .authorizeRequests()
                 .antMatchers("/api/public/**").permitAll()
                 .anyRequest().authenticated()
                 .and()
                 .httpBasic();
         }
     }
     ```

---

## Exemplo de uma API Completa em Spring Boot

```java
@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
        Usuario usuario = usuarioService.findById(id);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<Usuario> createUsuario(@Valid @RequestBody Usuario usuario) {
        Usuario createdUsuario = usuarioService.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @Valid @RequestBody Usuario usuarioDetails) {
        Usuario updatedUsuario = usuarioService.update(id, usuarioDetails);
        return ResponseEntity.ok(updatedUsuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o id " + id));
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario update(Long id, Usuario usuarioDetails) {
        Usuario usuario = findById(id);
        usuario.setNome(usuarioDetails.getNome());
        usuario.setEmail(usuarioDetails.getEmail());
        return usuarioRepository.save(usuario);
    }

    public void delete(Long id) {
        Usuario usuario = findById(id);
        usuarioRepository.delete(usuario);
    }
}

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;

    // Getters and Setters
}

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
```
