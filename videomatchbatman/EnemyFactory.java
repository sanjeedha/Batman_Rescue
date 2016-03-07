/**
 * Write a description of class EnemyFactory here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnemyFactory  
{
    public Enemy selectEnemyFactory(int newEnemyType)
    {
        Enemy newEnemy = null;
        if(newEnemyType == 0)
        {
            Enemy2Factory newEnemy2Factory = new Enemy2Factory();
            newEnemy = newEnemy2Factory.makeEnemy();
            //return newEnemy;
        }
        
        else if(newEnemyType == 1)
        {
            Enemy3Factory newEnemy3Factory = new Enemy3Factory();
            newEnemy = newEnemy3Factory.makeEnemy();
            //return newEnemy;
        }
        
        else if(newEnemyType == 2)
        {
            Enemy1Factory newEnemy1Factory = new Enemy1Factory();
            newEnemy = newEnemy1Factory.makeEnemy();
            //return newEnemy;   
        }
        //else return newEnemy;
        return newEnemy;
    }
}
