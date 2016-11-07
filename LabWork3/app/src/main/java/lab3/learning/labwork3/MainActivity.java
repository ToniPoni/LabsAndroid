package lab3.learning.labwork3;

import android.content.res.Resources;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mAccelSensor, mLightSensor, mProximitySensor, mMagneticSensor;
    private TextView mAccelValue, mLightValue, mProximityValue, mMagneticValue;

    /*
    * 15. В методе onPause() впишите код,
    * выполняющий отмену регистрации
    * обработчика событий (отписку от событий):
    */
    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
    /*
    14. В методе onResume впишите код,
     выполняющий регистрацию обработчика событий обновления показаний всех
     датчиков (подписку на события):
     */
    @Override
    protected void onPostResume() {
        super.onPostResume();
        if (mAccelSensor != null) {
            mSensorManager.registerListener(this, mAccelSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }

        if (mLightSensor != null) {
            mSensorManager.registerListener(this, mLightSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }

        if (mProximitySensor != null) {
            mSensorManager.registerListener(this, mProximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
        }

        if (mMagneticSensor != null) {
            mSensorManager.registerListener(this, mMagneticSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
        /*
        Обратите внимание, что в качестве обработчика
        для всех сенсоров используется объект активности,
        реализующий интерфейс SensorEventListener.
        То есть, при обновлении любого из датчиков будет
        вызываться метод onSensorChanged().
         */
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //инициализация сервисов сенсоров и формирующие ссылки на классы отдельных сенсоров
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mLightSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mProximitySensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        mMagneticSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        //инициализация ссылок на TextView для отображения значений
        mAccelValue = (TextView) findViewById(R.id.textViewAccelValue);
        mLightValue = (TextView) findViewById(R.id.textViewLightValue);
        mProximityValue = (TextView) findViewById(R.id.textViewProximityValue);
        mMagneticValue = (TextView) findViewById(R.id.textViewMagneticValue);
        //12. После инициализации ссылок на TextView в методе onCreate впишите код,
        // который определяет наличие датчиков
        // и при их отсутствии выводит соответствующий текст:
        Resources res = getResources();

        if (mAccelSensor == null) {
            mAccelValue.setText(res.getString(R.string.no_sensor));
        }
        if (mLightSensor == null) {
            mLightValue.setText(res.getString(R.string.no_sensor));
        }
        if (mProximitySensor == null) {
            mProximityValue.setText(res.getString(R.string.no_sensor));
        }
        if (mMagneticSensor == null) {
            mMagneticValue.setText(res.getString(R.string.no_sensor));
        }
        /*
        Обратите внимание, что текст об отсутствии датчика формируется
        при помощи объекта класса Resources,
        который получен вызовом getResources().
         */

    }

    /*
    17. В методе onSensorChanged() впишите код, отображающий показания акселерометра
     */
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor == mAccelSensor) {
            Resources res = getResources();

            float accelX = sensorEvent.values[0],
                    accelY = sensorEvent.values[1],
                    accelZ = sensorEvent.values[2],
                    accelTotal = (float)Math.sqrt(accelX*accelX + accelY * accelY + accelZ * accelZ);

            mAccelValue.setText(
                    String.format(
                            res.getString(R.string.three_dimension_value_format),
                            accelX, accelY, accelZ, accelTotal));
            /*
    Обратите внимание, что код выполняется только в том случае,
    если обработчик был вызван для акселерометра,
    а не какого-либо другого датчика
    (условие event.sensor == mAccelSensor)
     */
            /*
            18. Допишите код, выполняющий отображение показаний других датчиков
             */
        } else if (sensorEvent.sensor == mLightSensor) {
            mLightValue.setText(String.format("%1$.2f", sensorEvent.values[0]));
        }
        else if (sensorEvent.sensor == mProximitySensor) {
            mProximityValue.setText(String.format("%1$.2f", sensorEvent.values[0]));
        }
        else if (sensorEvent.sensor == mMagneticSensor) {
            Resources res = getResources();

            float magnetX = sensorEvent.values[0],
                    magnetY = sensorEvent.values[1],
                    magnetZ = sensorEvent.values[2],
                    magnetTotal = (float)Math.sqrt(magnetX*magnetX + magnetY * magnetY + magnetZ * magnetZ);

            mMagneticValue.setText(
                    String.format(
                            res.getString(R.string.three_dimension_value_format),
                            magnetX, magnetY, magnetZ, magnetTotal));
        }
    }



    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
