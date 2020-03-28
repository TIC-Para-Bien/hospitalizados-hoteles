import React from 'react';
import './HealthFeedback.scss';
import { Title } from '../../components/Title';
import { Temperature } from '../../components/Temperature';
import { BooleanChoice } from '../../components/BooleanChoice';
import { Textarea } from '../../components/Textarea';

export const HealthFeedback: React.FC = () => {
  return (
    <div className="HealthFeedback">
      <div className="notification">
        <h3 className="subtitle">
          Nota: Este formulario solo sera accesible por los pacientes, una vez que han
          hecho login.
        </h3>
      </div>

      <Title>Recogida de datos de paciente</Title>

      <h2 className="subtitle">¿Cómo se encuentra?</h2>

      <form className="health-feedback-form">
        <Temperature />

        <BooleanChoice label="Tos:" name="tos" />

        <BooleanChoice label="Dificultad para respirar:" name="respirar" />

        <BooleanChoice label="Flemas:" name="flemas" />

        <BooleanChoice label="Dolor de garganta:" name="garganta" />

        <BooleanChoice label="Perdida de gusto/olfato:" name="olfato" />

        <BooleanChoice label="Palpitaciones:" name="palpitaciones" />

        <BooleanChoice label="Diarrea:" name="diarrea" />

        <BooleanChoice label="Dolor de cabeza:" name="cabeza" />

        <BooleanChoice label="Dolor muscular o articular:" name="muscular" />

        <Textarea label="Observaciones:" placeholder="Observaciones..." />

        <div className="field">
          <div className="control">
            <button className="button is-primary">Enviar datos</button>
          </div>
        </div>
      </form>
    </div>
  );
};
