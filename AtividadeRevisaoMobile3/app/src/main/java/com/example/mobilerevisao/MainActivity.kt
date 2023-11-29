import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material3.icons.Icons
import androidx.compose.material3.icons.filled.ArrowBack
import androidx.compose.material3.icons.filled.Person
import androidx.compose.material3.icons.filled.School
import androidx.compose.material3.rememberTextFieldColors
import androidx.compose.material3.text.input.ImeAction
import androidx.compose.material3.textinput.TextInputDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.featherandroidtasks.ui.theme.FeatherAndroidTasksTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FeatherAndroidTasksTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var currentScreen by remember { mutableStateOf(Screen.Cadastro) }

                    when (currentScreen) {
                        Screen.Cadastro -> {
                            CadastroScreen(
                                onCadastroAlunoClick = { currentScreen = Screen.CadastroAluno },
                                onCadastroCursoClick = { currentScreen = Screen.CadastroCurso }
                            )
                        }
                        Screen.CadastroAluno -> {
                            CadastroAlunoScreen(
                                onVoltarClick = { currentScreen = Screen.Cadastro }
                            )
                        }
                        Screen.CadastroCurso -> {
                            CadastroCursoScreen(
                                onVoltarClick = { currentScreen = Screen.Cadastro }
                            )
                        }
                    }
                }
            }
        }
    }

    enum class Screen {
        Cadastro,
        CadastroAluno,
        CadastroCurso
    }
}

@Composable
fun CadastroScreen(
    onCadastroAlunoClick: () -> Unit,
    onCadastroCursoClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Campos de cadastro (nome, email, senha)

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onCadastroAlunoClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Cadastro de Aluno")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onCadastroCursoClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Cadastro de Curso")
        }
    }
}

@Composable
fun CadastroAlunoScreen(
    onVoltarClick: () -> Unit
) {
    CadastroFormScreen(
        onVoltarClick = onVoltarClick,
        title = "Cadastro de Aluno",
        icon = Icons.Default.Person
    )
}

@Composable
fun CadastroCursoScreen(
    onVoltarClick: () -> Unit
) {
    CadastroFormScreen(
        onVoltarClick = onVoltarClick,
        title = "Cadastro de Curso",
        icon = Icons.Default.School
    )
}

@Composable
fun CadastroFormScreen(
    onVoltarClick: () -> Unit,
    title: String,
    icon: ImageVector
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Voltar", modifier = Modifier.clickable { onVoltarClick() })
            Spacer(modifier = Modifier.width(16.dp))
            Text(title, style = MaterialTheme.typography.h5)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Campos do formulário
        var campo1 by remember { mutableStateOf("") }
        var campo2 by remember { mutableStateOf("") }
        var campo3 by remember { mutableStateOf("") }

        TextField(
            value = campo1,
            onValueChange = { campo1 = it },
            label = { Text("Campo 1") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        TextField(
            value = campo2,
            onValueChange = { campo2 = it },
            label = { Text("Campo 2") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        TextField(
            value = campo3,
            onValueChange = { campo3 = it },
            label = { Text("Campo 3") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // Implemente a lógica de envio do formulário
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Enviar")
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CadastroScreenPreview() {
    CadastroScreen({}, {})
}

@Composable
@Preview(showBackground = true)
fun CadastroAlunoScreenPreview() {
    CadastroAlunoScreen({})
}

@Composable
@Preview(showBackground = true)
fun CadastroCursoScreenPreview() {
    CadastroCursoScreen({})
}

@Composable
@Preview(showBackground = true)
fun CadastroFormScreenPreview() {
    CadastroFormScreen({}, "Cadastro de Aluno", Icons.Default.Person)
}
