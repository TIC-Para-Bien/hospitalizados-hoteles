import React from 'react';

const PatientLogin: React.FC = () => {
  return (
    <div className="PatientLogin">
        <h1>Acceso pacientes</h1>
        <form>
          <label>Número de habitación:</label>
          <input type="text" name="room"/>
          <br/>

          <label>Teléfono móvil:</label>
          <input type="text" name="phone"/>
          <br/>

          <button>Acceder</button>
        </form>
    </div>
  );
};

export default PatientLogin;
