# Desafio de projeto - Task Manager

Essa documentação foi desenvolvida por **Gabriel Rosa**. Em caso de dúvida, estou disponibilizando minhas redes sociais para contato.

[![LinkedIn](https://img.shields.io/badge/LinkedIn-gabriel--rosaa-blue?logo=linkedin)](https://www.linkedin.com/in/gabriel-rosaa/) [![GitHub](https://img.shields.io/badge/GitHub-Gabriel--Pink-black?logo=github)](https://github.com/Gabriel-Pink) [![Discord](https://img.shields.io/badge/Discord-gabriel.tec-%237289DA?logo=discord)]
[![Whatsapp](https://img.shields.io/badge/Whatsapp-(11)%2091356--4300-%237289DA?logo=whatsapp)](https://wa.me/+5511913564300) 

---

Este foi o primeiro desafio de projeto desenvolvido por mim, referente à aula: `Publicando Sua API REST na Nuvem Usando Spring Boot 3, Java 17 e Railway`. O instrutor deixou claro que o tema ficou livre para que o aluno pudesse escolher o que melhor se ajustasse à sua realidade.

- O tema escolhido por mim foi um gerenciador de tasks no qual o usuário pode criar novas coleções de tarefas.
- Desenvolvi um sistema de autenticação com JWT, para que cada usuário pudesse ter um controle próprio de todas as suas tasks.
- Utilizei PostgreSQL para armazenamento de dados.

Aqui está um overview de todas as rotas em que você pode realizar autenticação.

## Autenticação

### Registrar

![image](https://github.com/user-attachments/assets/016c6e21-cbd8-4b66-9e1a-dd73db88171b)


Neste exemplo, você pode utilizar a rota para realizar uma autenticação:

```
https://desafioprojeto-taskmanager-production.up.railway.app/auth/register
```

Passe os parâmetros JSON no body para criar uma conta:

```json
{
  "email": "usuario@email.com",
  "password": "senha123",
  "name": "teste"
}
```

Se for bem-sucedido, retornará um status 200 com as seguintes informações:

```json
{

    "id": 2,
    "name": "teste",
    "email": "usuario@email.com",
    "password": "$2a$10$vYR/jnEuQPfRtfvvj/ye5e8GNWY7/T.z3d1DPQpqAr8reS4UN7lWW",
    "collections": null
}
```

### Logar

![image](https://github.com/user-attachments/assets/5a269de7-f6f1-43fa-98fe-8ba8524c1455)

Neste exemplo, voce consegue utilizar a rota para realizar um login:

```
POST
https://desafioprojeto-taskmanager-production.up.railway.app/auth/login
```

Passe os parametros json no body para poder criar uma conta:

```json
{
    "email": "usuario@email.com",
    "password": "senha123"
}
```

Se sucesso, retornara um status 200 com as seguintes informacoes:

```jwt
eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyIiwiZW1haWwiOiJ1c3VhcmlvQGVtYWlsLmNvbSIsIm5hbWUiOiJ0ZXN0ZSIsImlhdCI6MTc0MDI5NDQ3NiwiZXhwIjoxNzQwMjk4MDc2fQ.1frpdc3BOcAAZrDTk4BLFxwcTM_2T1zxOYY5BMqy-NA
```


## Coleções


Os exemplos abaixo requerem que um token JWT seja passado nos headers da requisição.

```json
{
	"Authorization": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyIiwiZW1haWwiOiJ1c3VhcmlvQGVtYWlsLmNvbSIsIm5hbWUiOiJ0ZXN0ZSIsImlhdCI6MTc0MDI5NDQ3NiwiZXhwIjoxNzQwMjk4MDc2fQ.1frpdc3BOcAAZrDTk4BLFxwcTM_2T1zxOYY5BMqy-NA"
}
```

### Criar task

Com um usuário logado, estamos livres para consultar e criar tasks. Para criar uma, basta seguir o exemplo abaixo:

![image](https://github.com/user-attachments/assets/39012933-0258-426f-a202-c95aa3c22bda)

```
POST
https://desafioprojeto-taskmanager-production.up.railway.app/collections
```

Passe os parâmetros JSON no body para inserção de um texto:

```json
{
    "title": "texto 1"
}
```
Se sucesso, retornara um json como este:

```json
{
    "id": 2,
    "title": "texto 1",
    "userId": 2
}
```

### Consutar task

Com a task criada, estamos prontos para consultá-la. Segue o exemplo abaixo:

![image](https://github.com/user-attachments/assets/f28162f7-0f44-42a3-ad29-b9ce6f810a7d)


```
GET
https://desafioprojeto-taskmanager-production.up.railway.app/collections
```

Com isso, você consegue recuperar as tasks criadas pelo usuário que está logado:

```json
[
    {
        "id": 2,
        "title": "texto 1",
        "userId": 2
    },
    {
        "id": 3,
        "title": "texto 2",
        "userId": 2
    },
    {
        "id": 4,
        "title": "texto 3",
        "userId": 2
    },
    {
        "id": 5,
        "title": "texto 4",
        "userId": 2
    }
]
```
