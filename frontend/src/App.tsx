import React from 'react';
import HealthFeedback from './HealthFeedback';
import './App.css';
import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link
} from "react-router-dom";
import PatientLogin from './PatientLogin';
import SupervisorLogin from './SupervisorLogin';

const App: React.FC = () => {
  return (
    <div className="App">
        <Router>
            <div>
                <ul>
                    <li>
                        <Link to="/patients-login">Acceso Pacientes</Link>
                    </li>
                    <li>
                        <Link to="/supervisor-login">Acceso Personal Sanitario</Link>
                    </li>
                    <li>
                        <Link to="/patient-health-form">Recogida de datos de paciente</Link>
                    </li>
                </ul>

                <hr />

                <h3>TODO: Hacer portada con dos opciones nada mas, acceso pacientes o acceso sanitarios </h3>

                <Switch>
                    <Route exact path="/patients-login">
                        <PatientLogin />
                    </Route>
                    <Route path="/supervisor-login">
                        <SupervisorLogin />
                    </Route>
                    <Route path="/patient-health-form">
                        <HealthFeedback />
                    </Route>
                </Switch>
            </div>
        </Router>
    </div>
  );
}

export default App;
