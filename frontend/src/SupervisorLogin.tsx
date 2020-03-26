import React from 'react';

const SupervisorLogin: React.FC = () => {
  return (
    <div className="SupervisorLogin">
        <h1>Acceso supervisores</h1>
        <form>
          <label>Usuaria o usuario:</label>
          <input type="text" name="username"/>
          <br/>

          <label>Contraseña:</label>
          <input type="password" name="password"/>
          <br/>

          <button>Acceder</button>
        </form>
    </div>
  );
};

export default SupervisorLogin;
