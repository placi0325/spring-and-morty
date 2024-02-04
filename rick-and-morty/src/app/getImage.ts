const planet1 = '../../assets/planet1.webp';
const planet2 = '../../assets/planet2.webp';
const planet3 = '../../assets/planet3.jpg';
const planet4 = '../../assets/planet4.jpg';
const spacestation1 = '../../assets/spacestation.jpg';
const spacestation2 = '../../assets/spacestation2.webp';
const cluster = '../../assets/cluster.png';
const dream = '../../assets/dream.webp';
const menagerie = '../../assets/menagerie.webp';
const resort = '../../assets/resort.webp';
const tv = '../../assets/tv.jpg';
const other = '../../assets/other.png';

const planetImages = [planet1, planet2, planet3, planet4];
const spacestationImages = [spacestation1, spacestation2];

export function getImage(type: string): string {
  switch (type) {
    case 'Planet':
      return getRandomFromArray(planetImages);
    case 'Space station':
      return getRandomFromArray(spacestationImages);
    case 'Cluster':
      return cluster;
    case 'Dream':
      return dream;
    case 'Menagerie':
      return menagerie;
    case 'TV':
      return tv;
    case 'Resort':
      return resort;
    default:
      return other; // Default to the first planet image for unknown types
  }
}

function getRandomFromArray(array: string[]): string {
  const randomIndex = Math.floor(Math.random() * array.length);
  return array[randomIndex];
}
