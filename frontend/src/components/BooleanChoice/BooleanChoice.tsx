import * as React from 'react';

interface BooleanChoiceProps {
  label: string;
  name: string;
}

export const BooleanChoice: React.FC<BooleanChoiceProps> = ({ label, name }) => (
  <div className="field my-6">
    <label className="label">{label}</label>
    <div className="control has-text-centered">
      <label className="radio mx-2 p-3">
        <input type="radio" id={name} name={name} value="yes" className="mx-2" />
        Sí
      </label>
      <label className="radio mx-2 p-3">
        <input type="radio" id={name} name={name} value="no" className="mx-2" />
        No
      </label>
    </div>
    <p className="help is-danger">Invalid field</p>
  </div>
);

BooleanChoice.displayName = 'BooleanChoice';
