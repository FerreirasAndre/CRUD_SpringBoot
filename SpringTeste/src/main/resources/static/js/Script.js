document.addEventListener("DOMContentLoaded", () => {
    // Não chame carregarUsuarios() aqui para que a lista não seja carregada automaticamente.
});

const apiUrl = "http://localhost:8080/usuario";
const formUsuario = document.getElementById("formUsuario");
const listaUsuarios = document.getElementById("listaUsuarios");
const userIdInput = document.getElementById("userId");
const nomeInput = document.getElementById("nome");
const emailInput = document.getElementById("email");
const senhaInput = document.getElementById("senha");
const submitBtn = document.getElementById("submitBtn");
const cancelBtn = document.getElementById("cancelBtn");
const listarBtn = document.getElementById("listarBtn"); // <-- Novo botão

// READ: Carregar e listar todos os usuários
async function carregarUsuarios() {
    try {
        const resposta = await fetch(apiUrl);
        if (!resposta.ok) {
            throw new Error('Erro ao buscar usuários');
        }
        const usuarios = await resposta.json();
        listaUsuarios.innerHTML = "";
        usuarios.forEach(u => {
            const li = document.createElement("li");
            li.innerHTML = `
                <span>${u.id} - ${u.nome} (${u.email})</span>
                <button onclick="editarUsuario(${u.id})">Editar</button>
                <button onclick="excluirUsuario(${u.id})">Excluir</button>
            `;
            listaUsuarios.appendChild(li);
        });
    } catch (erro) {
        console.error('Erro:', erro);
    }
}

// CREATE e UPDATE: Lidar com o envio do formulário
formUsuario.addEventListener("submit", async (e) => {
    e.preventDefault();

    const usuario = {
        nome: nomeInput.value,
        email: emailInput.value,
        senha: senhaInput.value
    };

    const isEditing = userIdInput.value !== "";
    const method = isEditing ? "PUT" : "POST";
    const url = isEditing ? `${apiUrl}/${userIdInput.value}` : apiUrl;

    try {
        await fetch(url, {
            method: method,
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(usuario)
        });
        carregarUsuarios();
        resetarFormulario();
    } catch (erro) {
        console.error('Erro ao salvar usuário:', erro);
    }
});

// DELETE: Excluir um usuário
async function excluirUsuario(id) {
    if (confirm("Tem certeza que deseja excluir este usuário?")) {
        try {
            await fetch(`${apiUrl}/${id}`, {
                method: "DELETE"
            });
            carregarUsuarios();
        } catch (erro) {
            console.error('Erro ao excluir usuário:', erro);
        }
    }
}

// UPDATE: Preencher formulário para edição
async function editarUsuario(id) {
    try {
        const resposta = await fetch(`${apiUrl}/${id}`);
        const usuario = await resposta.json();
        userIdInput.value = usuario.id;
        nomeInput.value = usuario.nome;
        emailInput.value = usuario.email;
        senhaInput.value = usuario.senha;
        submitBtn.textContent = "Atualizar";
        cancelBtn.style.display = "inline-block";
    } catch (erro) {
        console.error('Erro ao buscar usuário para edição:', erro);
    }
}

// Resetar o formulário e botões
function resetarFormulario() {
    formUsuario.reset();
    userIdInput.value = "";
    submitBtn.textContent = "Salvar";
    cancelBtn.style.display = "none";
}

// Evento do botão de cancelar
cancelBtn.addEventListener("click", () => {
    resetarFormulario();
});

// Adicionando o evento de clique ao novo botão de listar
listarBtn.addEventListener("click", carregarUsuarios);