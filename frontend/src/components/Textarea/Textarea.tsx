import * as React from 'react';

interface TextareaProps {
  label: string;
  placeholder: string;
}

export const Textarea: React.FC<TextareaProps> = ({ label, placeholder }) => (
  <div className="field my-6">
    <label className="label">{label}</label>
    <div className="control has-text-centered">
      <textarea className="textarea" placeholder={placeholder}></textarea>
    </div>
    <p className="help is-danger">Invalid field</p>
  </div>
);

Textarea.displayName = 'Textarea';
