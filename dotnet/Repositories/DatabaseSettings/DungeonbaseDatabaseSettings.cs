using dungeonbase.Models.Interfaces;

namespace dungeonbase.Models.DatabaseSettings
{
    namespace BooksApi.Models
    {
        public class DungeonbaseDatabaseSettings : IDungeonbaseDatabaseSettings
        {
            public string LocationsCollectionName { get; set; }
            public string ItemsCollectionName { get; set; }
            public string PlayersCollectionName { get; set; }
            public string ConnectionString { get; set; }
            public string DatabaseName { get; set; }
        }
    }
}