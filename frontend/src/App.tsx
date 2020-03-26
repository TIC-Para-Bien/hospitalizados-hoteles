import React from 'react';
import { HealthFeedback } from './containers/HealthFeedback';
import './App.css';
import { BrowserRouter as Router, Switch, Route, Link } from 'react-router-dom';
import PatientLogin from './PatientLogin';
import SupervisorLogin from './SupervisorLogin';

const App: React.FC = () => {
  return (
    <div className="App">
      <Router>
        <div>
          <section className="section">
            <div className="container">
              <div className="columns">
                <div className="column">
                  <h1 className="title">
                    <Link to="/patients-login">Acceso Pacientes</Link>
                  </h1>
                </div>
                <div className="column">
                  <h1 className="title">
                    <Link to="/supervisor-login">Acceso Personal Sanitario</Link>
                  </h1>
                </div>
                <div className="column">
                  <h1 className="title">
                    <Link to="/patient-health-form">Recogida de datos de paciente</Link>
                  </h1>
                </div>
              </div>
            </div>

            <hr />

            <div className="container">
              <div className="notification">
                <h3 className="subtitle">
                  TODO: Hacer portada con dos opciones nada mas, acceso pacientes o acceso
                  sanitarios
                </h3>
              </div>

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
          </section>
        </div>
      </Router>
    </div>
  );
};

export default App;
