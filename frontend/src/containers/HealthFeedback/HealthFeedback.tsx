import React from 'react';
import './HealthFeedback.scss';

export const HealthFeedback: React.FC = () => {
  return (
    <div className="HealthFeedback">
      <div className="notification">
        <h3 className="subtitle">
          Nota: Este formulario solo sera accesible por los pacientes, una vez que han
          hecho login.
        </h3>
      </div>

      <h1 className="title">Recogida de datos de paciente</h1>

      <h2 className="subtitle">¿Cómo se encuentra?</h2>

      <form className="health-feedback-form">
        <div className="field my-6">
          <label className="label">Temperatura:</label>
          <div className="control has-text-centered">
            <div className="select">
              <select name="temperature-integers" defaultValue="36">
                <option>35</option>
                <option>36</option>
                <option>37</option>
                <option>38</option>
                <option>39</option>
                <option>40</option>
                <option>41</option>
              </select>
            </div>
            <span className="mx-1">.</span>
            <div className="select">
              <select name="temperature-decimals" defaultValue="7">
                <option>0</option>
                <option>1</option>
                <option>2</option>
                <option>3</option>
                <option>4</option>
                <option>5</option>
                <option>6</option>
                <option>7</option>
                <option>8</option>
                <option>9</option>
              </select>
            </div>
          </div>
          <p className="help is-danger">Invalid field</p>
        </div>

        <div className="field my-6">
          <label className="label">Tos:</label>
          <div className="control has-text-centered">
            <label className="radio mx-2 p-3">
              <input type="radio" id="tos" name="tos" value="yes" className="mx-2" />
              Sí
            </label>
            <label className="radio mx-2 p-3">
              <input type="radio" id="tos" name="tos" value="no" className="mx-2" />
              No
            </label>
          </div>
          <p className="help is-danger">Invalid field</p>
        </div>

        <div className="field my-6">
          <label className="label">Dificultad para respirar:</label>
          <div className="control has-text-centered">
            <label className="radio mx-2 p-3">
              <input
                type="radio"
                id="respirar"
                name="respirar"
                value="yes"
                className="mx-2"
              />
              Sí
            </label>
            <label className="radio mx-2 p-3">
              <input
                type="radio"
                id="respirar"
                name="respirar"
                value="no"
                className="mx-2"
              />
              No
            </label>
          </div>
          <p className="help is-danger">Invalid field</p>
        </div>

        <div className="field my-6">
          <label className="label">Flemas:</label>
          <div className="control has-text-centered">
            <label className="radio mx-2 p-3">
              <input
                type="radio"
                id="flemas"
                name="flemas"
                value="yes"
                className="mx-2"
              />
              Sí
            </label>
            <label className="radio mx-2 p-3">
              <input type="radio" id="flemas" name="flemas" value="no" className="mx-2" />
              No
            </label>
          </div>
          <p className="help is-danger">Invalid field</p>
        </div>

        <div className="field my-6">
          <label className="label">Dolor de garganta:</label>
          <div className="control has-text-centered">
            <label className="radio mx-2 p-3">
              <input
                type="radio"
                id="garganta"
                name="garganta"
                value="yes"
                className="mx-2"
              />
              Sí
            </label>
            <label className="radio mx-2 p-3">
              <input
                type="radio"
                id="garganta"
                name="garganta"
                value="no"
                className="mx-2"
              />
              No
            </label>
          </div>
          <p className="help is-danger">Invalid field</p>
        </div>

        <div className="field my-6">
          <label className="label">Perdida de gusto/olfato:</label>
          <div className="control has-text-centered">
            <label className="radio mx-2 p-3">
              <input
                type="radio"
                id="olfato"
                name="olfato"
                value="yes"
                className="mx-2"
              />
              Sí
            </label>
            <label className="radio mx-2 p-3">
              <input type="radio" id="olfato" name="olfato" value="no" className="mx-2" />
              No
            </label>
          </div>
          <p className="help is-danger">Invalid field</p>
        </div>

        <div className="field my-6">
          <label className="label">Palpitaciones:</label>
          <div className="control has-text-centered">
            <label className="radio mx-2 p-3">
              <input
                type="radio"
                id="palpitaciones"
                name="palpitaciones"
                value="yes"
                className="mx-2"
              />
              Sí
            </label>
            <label className="radio mx-2 p-3">
              <input
                type="radio"
                id="palpitaciones"
                name="palpitaciones"
                value="no"
                className="mx-2"
              />
              No
            </label>
          </div>
          <p className="help is-danger">Invalid field</p>
        </div>

        <div className="field my-6">
          <label className="label">Diarrea:</label>
          <div className="control has-text-centered">
            <label className="radio mx-2 p-3">
              <input
                type="radio"
                id="diarrea"
                name="diarrea"
                value="yes"
                className="mx-2"
              />
              Sí
            </label>
            <label className="radio mx-2 p-3">
              <input
                type="radio"
                id="diarrea"
                name="diarrea"
                value="no"
                className="mx-2"
              />
              No
            </label>
          </div>
          <p className="help is-danger">Invalid field</p>
        </div>

        <div className="field my-6">
          <label className="label">Dolor de cabeza:</label>
          <div className="control has-text-centered">
            <label className="radio mx-2 p-3">
              <input
                type="radio"
                id="cabeza"
                name="cabeza"
                value="yes"
                className="mx-2"
              />
              Sí
            </label>
            <label className="radio mx-2 p-3">
              <input type="radio" id="cabeza" name="cabeza" value="no" className="mx-2" />
              No
            </label>
          </div>
          <p className="help is-danger">Invalid field</p>
        </div>

        <div className="field my-6">
          <label className="label">Dolor muscular o articular:</label>
          <div className="control has-text-centered">
            <label className="radio mx-2 p-3">
              <input
                type="radio"
                id="muscular"
                name="muscular"
                value="yes"
                className="mx-2"
              />
              Sí
            </label>
            <label className="radio mx-2 p-3">
              <input
                type="radio"
                id="muscular"
                name="muscular"
                value="no"
                className="mx-2"
              />
              No
            </label>
          </div>
          <p className="help is-danger">Invalid field</p>
        </div>

        <div className="field my-6">
          <label className="label">Observaciones:</label>
          <div className="control has-text-centered">
            <textarea
              className="textarea"
              id="observaciones"
              name="observaciones"
              placeholder="Observaciones..."
            ></textarea>
          </div>
        </div>

        <div className="field">
          <div className="control has-centered-text">
            <button className="button is-primary">Enviar datos</button>
          </div>
        </div>
      </form>
    </div>
  );
};
