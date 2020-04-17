import React from 'react';
import { LineLayer, MapboxScene } from '@antv/l7-react';

export default React.memo(function Map() {
    const [ data, setData ] = React.useState();
    React.useEffect(() => {
        const fetchData = async () => {
            // const response = await fetch(
            //     'https://gw.alipayobjects.com/os/basement_prod/32e1f3ab-8588-46cb-8a47-75afb692117d.json',
            // );
            // const raw = await response.json();
            // setData(raw);
        };
        fetchData(); // 104.195397, 35.86166 | 138.252924, 36.204824
    }, []);
    return (
        <>
            <MapboxScene
                map={ {
                    center: [ 0, 0 ],
                    pitch: 0,
                    style: 'dark',
                    zoom: 1,
                } }
                style={ {
                    position: 'absolute',
                    top: 0,
                    left: 0,
                    right: 0,
                    bottom: 0,
                } }
            >
                { data && (
                    <LineLayer
                        key={ '2' }
                        source={ {
                            data,
                        } }
                        size={ {
                            values: 1,
                        } }
                        color={ {
                            values: '#fff',
                        } }
                        shape={ {
                            values: 'line',
                        } }
                        style={ {
                            opacity: 1,
                        } }
                    />
                ) }
            </MapboxScene>
        </>
    );
});