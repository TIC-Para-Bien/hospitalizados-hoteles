import * as React from 'react';

export const Temperature: React.FC<{}> = () => (
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
);

Temperature.displayName = 'Temperature';
