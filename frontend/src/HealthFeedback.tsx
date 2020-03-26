import React from 'react';

const HealthFeedback: React.FC = () => {
  return (
    <div className="HealthFeedback">
       Nota: Este formulario solo sera accesible por los pacientes, una vez que han hecho login.
        <h1>Recogida de datos de paciente</h1>
        <h2>¿Cómo se encuentra?</h2>
        <form>
          <label>Temperatura:</label>
          <input type="text" name="temperature-integers" placeholder="36" />.<input type="text" name="temperature-decimals" placeholder="7" />
          <br/>

          <label>Tos:</label>
          <input type="radio" id="tos" name="tos" value="yes"/>
          <label>Si</label>
          <input type="radio" id="tos" name="tos" value="no"/>
          <label>No</label>
          <br/>

          <label>Dificultad para respirar:</label>
          <input type="radio" id="respirar" name="respirar" value="yes"/>
          <label>Si</label>
          <input type="radio" id="respirar" name="respirar" value="no"/>
          <label>No</label>
          <br/>

          <label>Flemas:</label>
          <input type="radio" id="flemas" name="flemas" value="yes"/>
          <label>Si</label>
          <input type="radio" id="flemas" name="flemas" value="no"/>
          <label>No</label>
          <br/>

          <label>Dolor de garganta:</label>
          <input type="radio" id="garganta" name="garganta" value="yes"/>
          <label>Si</label>
          <input type="radio" id="garganta" name="garganta" value="no"/>
          <label>No</label>
          <br/>

          <label>Perdida de gusto/olfato:</label>
          <input type="radio" id="olfato" name="olfato" value="yes"/>
          <label>Si</label>
          <input type="radio" id="olfato" name="olfato" value="no"/>
          <label>No</label>
          <br/>

          <label>Palpitaciones:</label>
          <input type="radio" id="palpitaciones" name="palpitaciones" value="yes"/>
          <label>Si</label>
          <input type="radio" id="palpitaciones" name="palpitaciones" value="no"/>
          <label>No</label>
          <br/>

          <label>Diarrea:</label>
          <input type="radio" id="diarrea" name="diarrea" value="yes"/>
          <label>Si</label>
          <input type="radio" id="diarrea" name="diarrea" value="no"/>
          <label>No</label>
          <br/>

          <label>Dolor de cabeza:</label>
          <input type="radio" id="cabeza" name="cabeza" value="yes"/>
          <label>Si</label>
          <input type="radio" id="cabeza" name="cabeza" value="no"/>
          <label>No</label>
          <br/>

          <label>Dolor muscular o articular:</label>
          <input type="radio" id="muscular" name="muscular" value="yes"/>
          <label>Si</label>
          <input type="radio" id="muscular" name="muscular" value="no"/>
          <label>No</label>
          <br/>

          <label>Observaciones:</label>
          <textarea id="observaciones" name="observaciones"/>
          <br/>

          <button>Enviar datos</button>
        </form>
    </div>
  );
};

export default HealthFeedback;
