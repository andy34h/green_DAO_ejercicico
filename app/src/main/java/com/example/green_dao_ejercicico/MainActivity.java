package com.example.green_dao_ejercicico;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.green_dao_ejercicio.DaoSession;
import com.example.green_dao_ejercicio.DaoMaster;
import com.example.green_dao_ejercicio.Login;
import com.example.green_dao_ejercicio.LoginDao;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private LoginDao loginDAO;

    private Button btnComprobar;
    private Button btnRegistrarse;
    private Button btnBorrar;
    private EditText txtUsuario;
    private EditText txtPassword;
    private EditText txtID;
    private EditText txtLista;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(this, "Autenticacion", null);
        db = openHelper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        loginDAO = daoSession.getLoginDao();

        btnComprobar = (Button)findViewById(R.id.btnComprobar);
        btnRegistrarse = (Button)findViewById(R.id.btnRegistrarse);
        btnBorrar = (Button)findViewById(R.id.btnBorrar);
        txtUsuario = (EditText)findViewById(R.id.edtUsuario);
        txtPassword = (EditText)findViewById(R.id.edtPassword);
        txtID = (EditText)findViewById(R.id.edtID);
        txtLista=findViewById(R.id.txtLista);

        //limpiar
        loginDAO.deleteAll();


    }
    public void registrarCredenciales(View view)
    {
        try{
            Login login = new Login();
            String usuario = txtUsuario.getText().toString();
            String password = txtPassword.getText().toString();
            //Comprobamos que los campos no estén vacíos
            if((usuario.equals("")))
            {
                Toast.makeText(this, "Debe indicar el Usuario.", Toast.LENGTH_LONG).show();
            }
            else if(password.equals(""))
            {
                Toast.makeText(this, "Debe indicar el Password de Usuario.", Toast.LENGTH_LONG).show();
            }
            else
            {
                login.setUsuario(txtUsuario.getText().toString());
                login.setPassword(txtPassword.getText().toString());
                loginDAO.insert(login);
                Toast.makeText(this, "Datos almacenado->\nID: " + login.getId() + "\nUsuario: " + login.getUsuario(), 3000).show();
            }
            Log.d("GreenDAO", "Nuevo usuario almacenado, ID: " + login.getId());
        }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    mostrarTodos();
    }
    //Comprobar si existe Usuario
    public void iniciarSesion(View view)
    {
        String usuario = txtUsuario.getText().toString();
        List<Login> listUsuario = this.loginDAO.queryBuilder().where(LoginDao.Properties.Usuario.eq(usuario)).list();
        if(usuario.equals(""))
        {
            Toast.makeText(this, "Debe indicar el Usuario a comprobar.",Toast.LENGTH_LONG).show();
        }

        else
        {
            if(listUsuario!= null)
            {
                for(Login login : listUsuario)
                {
                    Toast.makeText(this, "El usuario indicado existe en la Base de Datos." +
                            "\nID:" + login.getId() + "\nUsuario:" + login.getUsuario(),Toast.LENGTH_LONG).show();
                }
            }
            else
            {
                Toast.makeText(this, "No hay coincidencias.", Toast.LENGTH_LONG).show();
            }
        }
      mostrarTodos();
    }
    //Borrar usuario por id
    public void borrarRegistro(View vew)
    {
        try{
            String id = txtID.getText().toString();
            if(id.equals(""))
            {
                Toast.makeText(this, "Debe indicar el id del usuario a borrar.", Toast.LENGTH_LONG).show();
            }
            else
            {
                Long identificador = Long.parseLong(id);
                loginDAO.deleteByKey(identificador);
                Toast.makeText(this, "Datos borrados->\nID: " + identificador, Toast.LENGTH_LONG).show();
            }
        }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
       mostrarTodos();
    }


    public void mostrarTodos()
    {
        txtLista.setText("");
        List<Login> listUsuariooo = this.loginDAO.queryBuilder().list();
        for(int i=0;i<listUsuariooo.size();i++){
       txtLista.setText(txtLista.getText()+" "+listUsuariooo.get(i).getId().toString()+"  "+listUsuariooo.get(i).getUsuario().toString());}
    }

}
