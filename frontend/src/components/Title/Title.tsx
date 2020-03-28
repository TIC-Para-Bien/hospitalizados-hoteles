import * as React from 'react';

export const Title: React.FC<{}> = ({ children }) => (
  <h1 className="title">{children}</h1>
);

Title.displayName = 'Title';
