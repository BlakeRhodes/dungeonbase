namespace dungeonbase.Models.Interfaces
{
    public interface IDungeonbaseDatabaseSettings
    {
        string LocationsCollectionName { get; set; }
        string ItemsCollectionName { get; set; }
        string PlayersCollectionName { get; set; }
        string ConnectionString { get; set; }
        string DatabaseName { get; set; }
    }
}